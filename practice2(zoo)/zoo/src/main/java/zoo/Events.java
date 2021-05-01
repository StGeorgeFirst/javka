package zoo;

/**
 *  Contains a possible events for the zoo-to-animals communication
 */
public enum Events {
    /**
     *  describes a visit of a zoo keeper
     */
    KEEPER_VISIT,

    /**
     *  describes a feeding of animals
     */
    FEEDING,

    /**
     *  describes the drinking of animals
     */
    DRINKING,

    /**
     *  describes a beginning of night time
     */
    NIGHT,

    /**
     *  describes a beginning of morning
     */
    MORNING,

    /**
     *  describes a thunder strike
     */
    THUNDER,

    /**
     *  describes the beginning of rain
     */
    RAIN,

    /**
     *  describes an event, when animals should update their states,
     *  according to zoo info fields
     */
    UPDATE_STATE


}
