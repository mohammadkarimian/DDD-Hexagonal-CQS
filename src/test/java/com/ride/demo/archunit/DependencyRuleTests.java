package com.ride.demo.archunit;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class DependencyRuleTests {

    @Test
    void validateRegistrationContextArchitecture() {
        HexagonalArchitecture.boundedContext("com.ride.demo")
            .withDomainLayer("domain")
            .withAdaptersLayer("adapter")
                .incoming("in.web")
                .outgoing("out.persistence")
            .and()
                .withQueryLayer("queries")
            .withApplicationLayer("application")
                .services("service")
                    .incomingPorts("port.in")
                    .outgoingPorts("port.out")
            .and()
            .withConfiguration("adapter.configuration")
            .check(new ClassFileImporter()
                    .importPackages("com.ride.demo.."));
    }

    @Test
    void testDomainAndApplicationPackageDependencies() {
        noClasses()
                .that()
                .resideInAPackage("com.ride.demo.domain..")
                .should()
                .dependOnClassesThat()
                .resideInAnyPackage("com.ride.demo.application..")
                .check(new ClassFileImporter()
                        .importPackages("com.ride.demo.."));
    }

}
