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
package com.ibm.codegen.github;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryContents;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.service.RepositoryService;

import com.ibm.codegen.model.v1.GithubRepository;

/*
 * Connect to Github projects
 */

public class GithubConnector {
    private final Repository gitrepo;
    
    public GithubConnector(GithubRepository repo) throws IOException {
        RepositoryService reposvc = new RepositoryService();
        gitrepo = reposvc.getRepository(repo.getOwner(), repo.getName());
    }
    
    public String getFileContents(String path) throws IOException {
        ContentsService contentsvc = new ContentsService();
        List<RepositoryContents> contents = contentsvc.getContents(gitrepo, path);
        String contentString = contents.get(0).getContent();
        byte[] decoded = Base64.getMimeDecoder().decode(contentString);
        String content = new String(decoded);
        return content;
    }
    
    public List<String> getFileList(String path) throws IOException {
        ContentsService contentsvc = new ContentsService();
        List<String> paths = new ArrayList<>();
        getFileList(contentsvc, path, paths);
        return paths;
    }
    
    private void getFileList(ContentsService contentsvc, String path, List<String> paths) {
        List<RepositoryContents> contents = Collections.emptyList();
        try {
            contents = contentsvc.getContents(gitrepo, path);
        } catch (IOException e) {
            e.printStackTrace();    //fix !
        }
        contents.forEach(data -> { 
            if(data.getType().equals(RepositoryContents.TYPE_FILE)) {
                paths.add(data.getPath()); 
            }
            if(data.getType().equals(RepositoryContents.TYPE_DIR)) {
                getFileList(contentsvc, data.getPath(), paths);
            }
        });
    }
}
