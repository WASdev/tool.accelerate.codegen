/*******************************************************************************
 * Copyright (c) 2016,17 IBM Corp.
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
package com.ibm.codegen.api.v1;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


@Path("/v1")
public class ProjectCodegenEndpoint {
    
    private static final Logger log = Logger.getLogger(ProjectCodegenEndpoint.class.getName());

    @GET
    @Path("/create")
    public String createProject() {
        log.log(Level.INFO, "Hit create endpoint.");
        return "This would create a project...";
    }

}