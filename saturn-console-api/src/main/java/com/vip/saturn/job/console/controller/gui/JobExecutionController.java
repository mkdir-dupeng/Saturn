/**
 * Copyright 2016 vip.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 *  the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * </p>
 **/

package com.vip.saturn.job.console.controller.gui;

import com.vip.saturn.job.console.controller.SuccessResponseEntity;
import com.vip.saturn.job.console.domain.RequestResult;
import com.vip.saturn.job.console.exception.SaturnJobConsoleException;
import com.vip.saturn.job.console.service.JobService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Execution status for specified job.
 *
 * @author kfchu
 */
@RequestMapping("/console/namespaces/{namespace:.+}/jobs/{jobName}/execution")
public class JobExecutionController extends AbstractGUIController {

	@Resource
	private JobService jobService;

	/**
	 * 获取作业执行状态
	 */
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success/Fail", response = RequestResult.class)})
	@GetMapping(value = "/status")
	public SuccessResponseEntity getExecutionStatus(final HttpServletRequest request, @PathVariable String namespace,
			@PathVariable String jobName) throws SaturnJobConsoleException {
		return new SuccessResponseEntity(jobService.getExecutionStatus(namespace, jobName));
	}

	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success/Fail", response = RequestResult.class)})
	@GetMapping(value = "/log")
	public SuccessResponseEntity getExecutionLog(final HttpServletRequest request, @PathVariable String namespace,
			@PathVariable String jobName, @RequestParam String jobItem) throws SaturnJobConsoleException {
		return new SuccessResponseEntity(jobService.getExecutionLog(namespace, jobName, jobItem));
	}
}
