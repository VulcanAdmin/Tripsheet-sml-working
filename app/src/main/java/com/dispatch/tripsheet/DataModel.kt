package com.dispatch.tripsheet

class Tripsheetlist (val videos: List<DataModel>)


class DataModel(
    var WOrder: Int,
    var DElNote: Int,
    var Company: String,
    var Weight: Int,
    var Button1: String,
    var Button2: String,
    var tvdone: String,
    var state: DataState = DataState.Unselected

    )
