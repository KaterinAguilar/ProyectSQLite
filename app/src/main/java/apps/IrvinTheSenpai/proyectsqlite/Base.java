package apps.IrvinTheSenpai.proyectsqlite;

public class Base {
    // nombre base de datos
    public static final String DB_NAME = "My_RECORDS_DB";
    //version de base de datos
    public static final int DB_VERSION = 1;
    //nombre de la tabla
    public static final String TABLE_NAME = "MY_RECORDS_TABLE";
    //columnas/campos de la tabla
    public static final String C_ID = "ID";
    public static final String C_NAME = "NAME";
    public static  final String C_IMAGE = "IMAGE";
   ;
    //Crea la tabla Query
    public static  final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +"("
            + C_ID+ " INTEGER PRIMARY KEY,"
            + C_NAME+ " TEXT,"
            + C_IMAGE+ " TEXT"

            + ")";
}
