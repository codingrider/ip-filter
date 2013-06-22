package org.chimi.ipfilter.web.impl;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IpBlockerImplTest {

    @Test
    public void configText() {
        Map<String, String> config = new HashMap<String, String>();
        config.put("type", "text");
        config.put("value",
                "order deny,allow\n" +
                        "allow from 1.2.3.4\n" +
                        "deny from 1.2.3.5");
        IpBlockerImpl ipBlocker = new IpBlockerImpl();
        ipBlocker.init(config);

        assertTrue(ipBlocker.accept("1.2.3.4"));
        assertFalse(ipBlocker.accept("1.2.3.5"));
    }

    @Test
    public void configFile() {
        Map<String, String> config = new HashMap<String, String>();
        config.put("type", "file");
        config.put("value",
                "ip-filter-web-simple/src/test/resources/test.conf");
        IpBlockerImpl ipBlocker = new IpBlockerImpl();
        ipBlocker.init(config);

        assertTrue(ipBlocker.accept("1.2.3.4"));
        assertFalse(ipBlocker.accept("1.2.3.5"));
    }

    @Test
    public void configClasspath() {
        Map<String, String> config = new HashMap<String, String>();
        config.put("type", "classpath");
        config.put("value",
                "/test.conf");
        IpBlockerImpl ipBlocker = new IpBlockerImpl();
        ipBlocker.init(config);

        assertTrue(ipBlocker.accept("1.2.3.4"));
        assertFalse(ipBlocker.accept("1.2.3.5"));
    }
}
