/*
 * Copyright 2015 the original author or authors.
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
package demo;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.restdocs.RestDocumentation.document;
import static org.springframework.restdocs.RestDocumentation.documentationConfiguration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FleetLocationServiceApplication.class)
@WebAppConfiguration
public class DemoApplicationMvcTests {

	@Autowired
	WebApplicationContext context;

	@Value("${org.springframework.restdocs.outputDir:target/generated-snippets}")
	private String restdocsOutputDir;

	private MockMvc mockMvc;

	@Before
	public void init() {
		System.setProperty("org.springframework.restdocs.outputDir",
				this.restdocsOutputDir);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
				.apply(documentationConfiguration()).build();
	}

	@Test
	public void getLocations() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/locations"))
		.andExpect(MockMvcResultMatchers.content()
				.string(containsString("_embedded")))
		.andDo(document("locations"));
	}

}
