/*
 * Copyright 2018 Pivotal, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.clouddriver.artifacts.ivy.settings;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class UrlResolverTest {
  @Test
  void patternsAreDeserialized() throws IOException {
    UrlResolver urlResolver =
        new XmlMapper()
            .readValue(
                "<url name=\"main\">\n"
                    + "  <ivy pattern=\"http://repo/[module]/[revision]/ivy-[revision].xml\"/>\n"
                    + "  <artifact pattern=\"http://repo/[module]/[revision]/[artifact]-[revision].[ext]\"/>\n"
                    + "</url>",
                UrlResolver.class);

    assertThat(urlResolver.getIvy()).hasSize(1);
    assertThat(urlResolver.getArtifact()).hasSize(1);
  }
}
