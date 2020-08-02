package de.boeg.rdf.generate.shacl.ocl;

import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;

import static java.lang.String.format;

@UtilityClass
@Slf4j
public class OCLRuleParser {
    public static void main(String[] args) {
        verifyArgs(args);
        var config = extractRunArgs(args);
        log.info(format("Reading from %s, parsing and writing to %s",
                config.input.toString(), config.output.toString()));
    }

    private void verifyArgs(String[] args) {
        if (args.length == 2)
            throw new IllegalArgumentException(
                    format("Expected two run arguments, was %d", args.length));
        if (Files.notExists(Path.of(args[0])))
            throw new IllegalArgumentException(
                    format("Input does not exist %s", args[0]));
        if (Files.exists(Path.of(args[1])))
            throw new IllegalArgumentException(
                    format("Output exist %s", args[0]));
    }

    private RunArgs extractRunArgs(String[] args) {
        return new RunArgs(Path.of(args[1]), Path.of(args[2]));
    }

    @RequiredArgsConstructor
    private static class RunArgs {
        final Path input;
        final Path output;
    }
}
