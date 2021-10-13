package ru.tuanviet.javabox;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class HttpClientTest {

    @Rule
    public WireMockRule wm = new WireMockRule(
            options().dynamicPort()
    );

    @Test
    public void shouldReturnIdsResponseForJsonArray() {
        // given
        final String testServiceUri = serviceWithResponse("/top-ids", "topnews-ids.json");

        // when
        TypeReference<List<String>> listType = new TypeReference<List<String>>() {
        };
        List<String> response = new HttpClient().fetch(wm.baseUrl() + testServiceUri, listType);

        // then
        assertThat(response).isEqualTo(asList("28852857", "28850036", "28850861", "28852109"));
    }

    private String serviceWithResponse(String serviceUri, String responseFile) {
        ResponseDefinitionBuilder response = aResponse()
                .withBodyFile("get-topnews-id/" + responseFile);
        givenThat(
                get(urlEqualTo(serviceUri)).willReturn(response)
        );
        return serviceUri;
    }

}
