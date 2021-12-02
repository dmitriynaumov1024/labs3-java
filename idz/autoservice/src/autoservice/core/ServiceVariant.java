package autoservice.core;

/**
 *
 * @author Dmitriy Naumov
 */
public enum ServiceVariant implements java.io.Serializable {
    Unknown,
    Diagnostics,
    RegularService,
    AfterCrashService
}
