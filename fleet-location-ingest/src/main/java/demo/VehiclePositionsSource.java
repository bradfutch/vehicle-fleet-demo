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


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Cloud Stream {@link Source}, responsible for ingesting vehicle position
 * data.
 *
 * @author Gunnar Hillert
 *
 */
@EnableBinding(Source.class)
@RestController
public class VehiclePositionsSource {

	@Autowired
	private MessageChannel output;

	@RequestMapping(path="/api/locations", method=RequestMethod.POST)
	public void locations(@RequestBody String positionInfo) {
		Message<String> msg = MessageBuilder.withPayload(positionInfo).build();
		this.output.send(msg);
	}

}
