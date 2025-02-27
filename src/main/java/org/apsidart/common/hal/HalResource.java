package org.apsidart.common.hal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HalResource<T> {

    private T content;

    @JsonProperty("_links")
    private Map<String, HalLink> links = new HashMap<>();

    @JsonProperty("_embedded")
    private List<HalResource<T>> embedded = new ArrayList<>();

    public HalResource<T> addLink(String rel, String href) {
        links.computeIfAbsent(rel, k -> new HalLink(href) );
        return this;
    }

    public void addEmbedded(HalResource<T> resource) {
        embedded.add(resource);
    }

    // Getters et setters
    public T getContent() { return content; }
    public HalResource<T> setContent(T content) { 
        this.content = content;
        return this; }
    public Map<String, HalLink> getLinks() { return links; }
    public List<HalResource<T>> getEmbedded() { return embedded; }
}
