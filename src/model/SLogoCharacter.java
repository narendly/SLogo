package model;

/**
 * SLogo's highest Character in hierarchy, to support multiple types of
 * characters
 *
 */

public interface SLogoCharacter {

    void setState(SLogoCharacterState myState);

    SLogoCharacterState getState();

}