package com.greensense.util.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CustomObjectMapper extends ObjectMapper {

        public CustomObjectMapper(){

            setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.NON_PRIVATE);
            setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
            setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE);
            setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE);
            setVisibility(PropertyAccessor.CREATOR, JsonAutoDetect.Visibility.PUBLIC_ONLY);
            enable(SerializationFeature.INDENT_OUTPUT);
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        }

}
