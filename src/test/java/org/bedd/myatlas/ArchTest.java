package org.bedd.myatlas;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("org.bedd.myatlas");

        noClasses()
            .that()
            .resideInAnyPackage("org.bedd.myatlas.service..")
            .or()
            .resideInAnyPackage("org.bedd.myatlas.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..org.bedd.myatlas.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
