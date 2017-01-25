/*******************************************************************************
 * Copyright (c) 2016 IBM Corp.
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
package com.ibm.codegen.it.api.v1;

import org.junit.Test;

import com.ibm.codegen.it.EndpointClient;

public class EndpointTest extends EndpointClient {
    private static final String URL_BASE = "/api/v1";
    
    @Test
    public void testDeployment() {
        testEndpoint(URL_BASE + "/create", "This would create a project...");
    }
    
    @Test
    public void testHealth() {
        testEndpoint(URL_BASE + "/health", "Status : OK");
    }
}
