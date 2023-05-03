package com.athena.projectgroupwareapp.drawer.attendance.recycler

import java.util.concurrent.TimeoutException

data class AttendanceItem(
    var today : String,
    var timeIn : String,
    var timeOut : String)

data class CalListItem (
    var title : String,
    var dateOfIssue : String)