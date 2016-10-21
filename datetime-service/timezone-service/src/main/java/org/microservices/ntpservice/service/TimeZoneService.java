/*******************************************************************************
 * ============================================================================
 * Copyright (C) Pfizer Inc.
 * All rights reserved
 * ============================================================================
 * Notice: All Rights Reserved.
 * This material contains the trade secrets and confidential information of
 * Pfizer Inc., which embody substantial creative effort, ideas and
 * expressions. No part of this material may be reproduced or transmitted
 * in any form or by any means, electronic, mechanical, optical or otherwise,
 * including photocopying and recording, or in connection with any information
 * storage or retrieval system, without written permission from:
 * <p/>
 * Hospira, a Pfizer company
 * 13520 Evening Creek Dr., Suite 200
 * San Diego, CA 92128
 * www.hospira.com
 *******************************************************************************/

package org.microservices.ntpservice.service;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.InetAddress;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 *
 */
@RestController
public class TimeZoneService {

    @Value("${timezone}")
    private String timezone;

    @RequestMapping(value = "/timezone", method = RequestMethod.GET)
    public String getTimezone() {
        return timezone;
    }

}
