package csmart.api.db;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by sethur on 2/10/2016.
 */
public interface JsonType {
    default JsonNode toJsonNode(){
        return new ObjectMapper().valueToTree(this);
    }

    static <$Model> $Model fromJsonNode(JsonNode jsonNode, Class<$Model> clz){
        return new ObjectMapper().convertValue(jsonNode, clz);
    }
}
