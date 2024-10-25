package org.service;
import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

import java.util.HashMap;
import java.util.Map;

public class DefaultNamespacePrefixMapper extends NamespacePrefixMapper
{
    private Map<String, String> namespaceMap = new HashMap<>();

    public DefaultNamespacePrefixMapper() {
        namespaceMap.put("http://www.dei.uc.pt/EAI", "h");

    }
    @Override
    public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
        return namespaceMap.getOrDefault(namespaceUri, suggestion);
    }
}