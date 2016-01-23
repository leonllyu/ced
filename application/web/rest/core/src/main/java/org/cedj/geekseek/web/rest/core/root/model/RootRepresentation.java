package org.cedj.geekseek.web.rest.core.root.model;

import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlRootElement;

import org.cedj.geekseek.web.rest.core.LinkableRepresentation;
import org.cedj.geekseek.web.rest.core.ResourceLink;

@XmlRootElement(name = "root", namespace = "urn:ced:root")
public class RootRepresentation extends LinkableRepresentation<ResourceLink> {

    // JAXB
    protected RootRepresentation() {}

    public RootRepresentation(String representationType, UriInfo uriInfo) {
        super(ResourceLink.class, representationType, uriInfo);
    }
}
