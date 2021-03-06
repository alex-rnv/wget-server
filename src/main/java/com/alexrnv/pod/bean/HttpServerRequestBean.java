package com.alexrnv.pod.bean;

import com.alexrnv.pod.json.WgetModule;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.MultiMap;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;

/**
 * Json serializable significant data from {@link HttpServerRequest}, required for passing data between
 * verticles via event bus.
 * TODO: replace with {@link MessageCodec}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HttpServerRequestBean {

    private static final Logger LOG = LoggerFactory.getLogger(HttpServerRequestBean.class);

    @JsonIgnore
    private static ObjectMapper mapper = new ObjectMapper()
            .registerModule(new WgetModule());

    @JsonProperty
    public String id;
    @JsonProperty
    public String absoluteUri;
    @JsonProperty
    public String uri;
    @JsonProperty
    public String path;
    @JsonProperty
    public String query;
    @JsonProperty
    public MultiMap headers;
    @JsonProperty
    public MultiMap params;

    private HttpServerRequestBean(){}

    public HttpServerRequestBean(HttpServerRequest request) {
        this(RandomStringUtils.randomAlphanumeric(8), request.absoluteURI(), request.uri(), request.path(), request.query(), request.headers(), request.params());
    }

    protected HttpServerRequestBean(String id, String absoluteUri, String uri, String path, String query, MultiMap headers, MultiMap params) {
        this.id = id;
        this.absoluteUri = absoluteUri;
        this.uri = uri;
        this.path = path;
        this.query = query;
        this.headers = headers;
        this.params = params;
    }

    public static HttpServerRequestBean fromJsonObject(JsonObject jsonObject) {
        String string = jsonObject.encode();
        try {
            return mapper.readValue(string, HttpServerRequestBean.class);
        } catch (IOException e) {
            LOG.error("Failed to read " + string, e);
        }
        return null;
    }

    public JsonObject asJsonObject() {
        try {
            return new JsonObject(mapper.writeValueAsString(this));
        } catch (JsonProcessingException e) {
            LOG.error("Failed to process " + this, e);
            return null;
        }
    }

    @Override
    public String toString() {
        return "HttpServerRequestBean{" +
                "id='" + id + '\'' +
                ", absoluteUri='" + absoluteUri + '\'' +
                ", uri='" + uri + '\'' +
                ", path='" + path + '\'' +
                ", query='" + query + '\'' +
                ", headers=" + BeanUtil.toString(headers) +
                ", params=" + BeanUtil.toString(params) +
                '}';
    }
}
