package com.ride.demo.archunit;

import com.tngtech.archunit.core.domain.JavaClasses;

import java.util.ArrayList;
import java.util.List;

public class QueryLayer extends ArchitectureElement {
    private final HexagonalArchitecture parentContext;

    QueryLayer(String basePackage, HexagonalArchitecture parentContext) {
        super(basePackage);
        this.parentContext = parentContext;
    }

    List<String> allAdapterPackages() {
        List<String> allAdapters = new ArrayList<>();
        return allAdapters;
    }

    public HexagonalArchitecture and() {
        return parentContext;
    }

    String getBasePackage() {
        return basePackage;
    }

    void dontDependOnEachOther(JavaClasses classes) {
        List<String> allAdapters = allAdapterPackages();
        for (String adapter1 : allAdapters) {
            for (String adapter2 : allAdapters) {
                if (!adapter1.equals(adapter2)) {
                    denyDependency(adapter1, adapter2, classes);
                }
            }
        }
    }


    void doesNotDependOn(String packageName, JavaClasses classes) {
        denyDependency(this.basePackage, packageName, classes);
    }

    void doesNotContainEmptyPackages() {
        denyEmptyPackages(allAdapterPackages());
    }
}
