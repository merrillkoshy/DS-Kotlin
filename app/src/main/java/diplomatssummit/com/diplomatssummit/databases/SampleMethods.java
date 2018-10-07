package diplomatssummit.com.diplomatssummit.databases;

import android.util.Log;
import android.widget.ImageView;

import com.raizlabs.android.dbflow.sql.language.OperatorGroup;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.squareup.picasso.Picasso;

import java.util.List;

import diplomatssummit.com.diplomatssummit.databases.core.MediaTable;
import diplomatssummit.com.diplomatssummit.databases.core.MediaTable_Table;

public class SampleMethods {

    private final String TAG = this.getClass().getSimpleName();

    public final static Long MEDIA_IMAGE = 1L;
    public final static Long MEDIA_VIDEO = 2L;

    public void readAllMedias() {

        List<MediaTable> mediaTableList = SQLite.select().from(MediaTable.class).queryList();

        for (MediaTable mediaTable: mediaTableList) {

            /*ImageView tempIv = null;
            Picasso.get().load(mediaTable.MediaUrl).into(tempIv);*/

            Log.d(TAG, "MediaTable - " + mediaTable.toString());
        }

        readMediaRowsBasedOnType(MEDIA_IMAGE);
        readMediaRowsBasedOnType(MEDIA_VIDEO);

        createMedia();
    }

    public void readMediaRowsBasedOnType(Long mediaType) {

        OperatorGroup operatorGroup = OperatorGroup.clause()
                .and(MediaTable_Table.MediaType.eq(mediaType));

        List<MediaTable> mediaRowList = SQLite.select()
                .from(MediaTable.class)
                .where(operatorGroup)
                .queryList();

        for (MediaTable mediaTable: mediaRowList) {
            Log.d(TAG, "MediaTable - " + mediaTable.toString());
        }
    }

    public void createMedia() {

        MediaTable mediaTable = new MediaTable();
        mediaTable.Id = 3L;
        mediaTable.MediaType = MEDIA_IMAGE;
        mediaTable.MediaUrl = "https://google.com/image.jpg";

        mediaTable.save();
    }
}
