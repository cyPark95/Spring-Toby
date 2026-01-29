package pcy.study.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class AutoConfigurationImportSelector implements DeferredImportSelector {

    private final ClassLoader classLoader;

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> autoConfigurations = new ArrayList<>();
        ImportCandidates.load(AutoConfiguration.class, classLoader).forEach(autoConfigurations::add);
        return autoConfigurations.toArray(new String[0]);
    }
}
