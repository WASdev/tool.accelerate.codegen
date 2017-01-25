/*******************************************************************************
 * Copyright (c) 2017 IBM Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.ibm.codegen.test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static com.ibm.codegen.test.Constants.*;

import org.junit.Test;

import com.ibm.codegen.github.GithubConnector;
import com.ibm.codegen.model.v1.Config;
import com.ibm.codegen.model.v1.GithubRepository;
import com.ibm.codegen.process.TemplateProcessor;

import mockit.Expectations;
import mockit.Mocked;

public class TestTemplateFileProcessing {

    @Mocked
    GithubRepository ghrepo;
    
    @Mocked
    Config config;
    
    @Test
    public void testProcessing() throws Exception {
        new Expectations() {{
            config.getArtifactId(); returns("test");
            ghrepo.getOwner(); returns(GITHUB_OWNER);
            ghrepo.getName(); returns(GITHUB_REPO);
        }};
        
        GithubConnector connector = new GithubConnector(ghrepo);
        String contents = connector.getFileContents("templates/build/liberty-pom.xml");
        
        TemplateProcessor processor = new TemplateProcessor();
        String result = processor.process(contents, config);
        assertThat(result, containsString("<artifactId>test</artifactId>"));
    }
}
