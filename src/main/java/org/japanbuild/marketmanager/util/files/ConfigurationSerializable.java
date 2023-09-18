package org.japanbuild.marketmanager.util.files;

import java.util.Map;

public interface ConfigurationSerializable {

    Map<String, Object> serialize();
}