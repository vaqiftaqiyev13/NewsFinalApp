package com.vagif_tagiyev.newsfinalapp.database

import androidx.room.TypeConverter
import javax.xml.transform.Source

class Convertor {
    @TypeConverter
    fun fromSource(source: com.vagif_tagiyev.newsfinalapp.model.Source) :String{
        return source.name
    }

    @TypeConverter
    fun toSource(name:String):com.vagif_tagiyev.newsfinalapp.model.Source{
        return com.vagif_tagiyev.newsfinalapp.model.Source(name,name)
    }


}