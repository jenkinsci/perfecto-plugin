package io.plugins.perfecto;

import java.util.logging.Logger;

public class BuildUtils
{
    private static final Logger log = Logger.getLogger(BuildUtils.class.getName());

    // ------------------------------------------------------------------------------------------------------- Constants

    private static final String version = "1.0";

    // -------------------------------------------------------------------------------------------------- Public Methods
    public static String getCurrentVersion()
    {
        return version;
    }
}

