import com.google.protobuf.gradle.*
plugins {
	id ("org.springframework.boot") version "2.7.5"
	id("io.freefair.lombok") version "6.5.1"
	id ("com.google.protobuf") version "0.9.1"
	java
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(17))
	}
}

repositories {
	mavenCentral()
}

sourceSets {
	main {
		proto {
			// In addition to the default 'src/main/proto'
			srcDir ("src/main/java/proto/")
		}
//		java {
//			srcDirs += ['build/generated/sources/proto/main/java',
//				'build/generated/source/proto/main/grpc']
//		}
	}
}

protobuf {
	// Configure the protoc executable (protobuf compiler)
	protoc {
		// Download from repositories
		artifact = "com.google.protobuf:protoc:3.21.6"
	}
	plugins {
		// Optional: an artifact spec for a protoc plugin, with "grpc" as
		// the identifier, which can be referred to in the "plugins"
		// container of the "generateProtoTasks" closure.
		id("grpc") {
			artifact = "io.grpc:protoc-gen-grpc-java:1.15.1"
		}
	}
	generateProtoTasks {
		ofSourceSet("main").forEach {
			it.plugins {
				// Apply the "grpc" plugin whose spec is defined above, without
				// options. Note the braces cannot be omitted, otherwise the
				// plugin will not be added. This is because of the implicit way
				// NamedDomainObjectContainer binds the methods.
				id("grpc") { }
			}
		}
	}
}

dependencies {
	//SpringBoot
	implementation ("org.springframework.boot:spring-boot-starter")
	implementation(platform("org.springframework.boot:spring-boot-dependencies:2.7.5"))
	developmentOnly ("org.springframework.boot:spring-boot-devtools")
	testImplementation ("org.springframework.boot:spring-boot-starter-test")
	implementation("org.springframework:spring-web:5.3.23")

	//gRRC Client
	implementation("com.google.protobuf:protobuf-java:3.21.8")
	implementation("net.devh:grpc-server-spring-boot-starter:2.13.1.RELEASE")

}

tasks.getByName<Test>("test") {
	useJUnitPlatform()
}