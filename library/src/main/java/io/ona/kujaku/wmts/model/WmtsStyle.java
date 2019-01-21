package io.ona.kujaku.wmts.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

import io.ona.kujaku.wmts.model.common.ows.LanguageStringType;

/**
 * Describes a Wmts Style object from the WMTS Capabilities object
 *
 * Created by Emmanuel Otin - eo@novel-t.ch 11/28/18.
 */
public class WmtsStyle {

    @Attribute(name="isDefault")
    private boolean isDefault = false;

    @ElementList(inline=true, entry="Title")
    private List<LanguageStringType> titles;

    @Element(name="Identifier")
    private String identifier;

    public boolean isDefault() {
        return this.isDefault;
    }

    public List<LanguageStringType> getTitles() {
        return this.titles;
    }

    public String getIdentifier() {
        return this.identifier;
    }
}
