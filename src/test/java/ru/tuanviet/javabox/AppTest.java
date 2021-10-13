package ru.tuanviet.javabox;


import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.*;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class AppTest {

    @Rule
    public WireMockRule wm = new WireMockRule(
            options().dynamicPort()
    );

    @Test
    public void shouldAnswerWithTrue() {
        assertThat(true).isTrue();
    }

    @Test
    public void shouldGetFormattedNewsFromTopNewsWithMock()  {
        //given
        final String testServiceUri = serviceWithResponse("/top-ids", "topnews-ids.json");
        //then
        List<String> testNews = new App(wm.baseUrl() + testServiceUri, 2).getFormattedNewsFromTopNews();
        //when
        assertThat(testNews).hasSize(2);

//        for (final String name : testListOfPersons) {
//            String[] nameParts = name.split(" ");
//            assertThat(nameParts.length).isEqualTo(2);
//        }
//
//        for (final String name : testListOfPersons) {
//            String[] nameParts = name.split(" ");
//            assertThat(nameParts[0]).isIn("Александр", "София");
//            assertThat(nameParts[1]).isIn("Абажур", "Абажурчик");
//        }
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
