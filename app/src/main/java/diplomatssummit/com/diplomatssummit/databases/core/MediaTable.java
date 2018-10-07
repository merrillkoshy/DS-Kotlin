package diplomatssummit.com.diplomatssummit.databases.core;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class)
public class MediaTable extends BaseModel {

    @Column
    public Long Id;

    @Column
    public Long MediaType;

    @PrimaryKey
    public String MediaUrl;

    @Override
    public String toString() {
        return "Id - " + Id + ",\nMediaType - " + MediaType + ",\nMediaUrl - " + MediaUrl;
    }
}
