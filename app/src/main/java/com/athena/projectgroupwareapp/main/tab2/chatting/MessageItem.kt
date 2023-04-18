package com.athena.projectgroupwareapp.main.tab2.chatting

data class MessageItem
    (
    var name: String,
    var id: String,
    var message: String,
    var imgUrl: String,
    var time: String,
    var othername: String,
    var otherprofileUrl: String,
    var otherId: String
)