/**
 * 
 */
package view;

import exception.SLogoException;
import model.ResourceLoader;

/**
 * Abstract builder class that are extended by other builders in View
 * 
 * @author Hunter
 * 
 */
public abstract class SLogoBuilder {
    
    protected static final String DEFAULT_RESOURCE_PACKAGE = "file:resources/resources/";
    protected static final String DEFAULT_LANG_PACKAGE = "resources.languages/";

    private ResourceLoader myResourceLoader;

    /**
     * Abstract class extended by different view builders
     * 
     * @throws SLogoException 
     */
    public SLogoBuilder () {
        myResourceLoader = new ResourceLoader("default.properties");
    }

    /**
     * @return the myResourceLoader
     */
    protected ResourceLoader getResourceLoader () {
        return myResourceLoader;
    }

    /**
     * @param myResourceLoader the myResourceLoader to set
     */
    protected void setResourceLoader (ResourceLoader myResourceLoader) {
        this.myResourceLoader = myResourceLoader;
    }
}