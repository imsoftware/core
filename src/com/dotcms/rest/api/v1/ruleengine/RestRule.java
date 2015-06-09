package com.dotcms.rest.api.v1.ruleengine;

import com.dotcms.repackage.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.dotcms.repackage.com.fasterxml.jackson.annotation.JsonProperty;
import com.dotcms.repackage.com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.dotcms.rest.exception.BadRequestException;
import java.util.Date;

import static com.dotcms.rest.validation.Preconditions.checkNotEmpty;

/**
 * Note: Pretend this class exists in a separate module from the core data types, and cannot have any knowledge of those types. Because it should.
 * @author Geoff M. Granum
 */
@JsonDeserialize(builder = RestRule.Builder.class)
public class RestRule {

    public final String id;
    public final String name;
    public final String fireOn;
    public final Boolean shortCircuit;
    public final String folder;
    public final Integer priority;
    public final Boolean enabled;


    private RestRule(Builder builder) {
        id = builder.id;
        name = builder.name;
        fireOn = builder.fireOn;
        shortCircuit = builder.shortCircuit;
        folder = builder.folder;
        priority = builder.priority;
        enabled = builder.enabled;
    }

    @JsonIgnoreProperties({"groups", "actions"})
    public static final class Builder {
        @JsonProperty private String id;
        @JsonProperty private String name;
        @JsonProperty private String fireOn;
        @JsonProperty private Boolean shortCircuit=false;
        @JsonProperty private String folder;
        @JsonProperty private Integer priority=0;
        @JsonProperty private Boolean enabled=false;

        /*
            RestRule restRule = new RestRule.Builder()
            .id( input.getId() )
            .name( input.getName() )
            .fireOn( input.getFireOn() )
            .shortCircuit( input.getShortCircuit() )
            .site( input.getsite() )
            .folder( input.getFolder() )
            .priority( input.getPriority() )
            .enabled( input.getEnabled() )
            .modDate( input.getModDate() )
            .build();
        */
        public Builder() {}

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder fireOn(String fireOn) {
            this.fireOn = fireOn;
            return this;
        }

        public Builder shortCircuit(boolean shortCircuit) {
            this.shortCircuit = shortCircuit;
            return this;
        }

        public Builder folder(String folder) {
            this.folder = folder;
            return this;
        }

        public Builder priority(int priority) {
            this.priority = priority;
            return this;
        }

        public Builder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder from(RestRule copy) {
            id = copy.id;
            name = copy.name;
            fireOn = copy.fireOn;
            shortCircuit = copy.shortCircuit;
            folder = copy.folder;
            priority = copy.priority;
            enabled = copy.enabled;
            return this;
        }


        public void validate(){
            checkNotEmpty(name, BadRequestException.class, "rule.name is required.");
        }

        public RestRule build() {
            this.validate();
            return new RestRule(this);
        }
    }
}
 
