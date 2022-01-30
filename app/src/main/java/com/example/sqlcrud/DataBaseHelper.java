package com.example.sqlcrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String TABELA_USUARIO = "tabela_usuario";
    public static final String NOME_USUARIO = "nome_usuario";
    public static final String IDADE_USUARIO = "idade_usuario";
    public static final String EHVIZINHO = "ehvizinho";
    public static final String ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "usuarios.db", null, 1);
    }

    // chamado a primeira vez que o banco de dados é criado, aqui fica o código da criação do DB
    @Override
    public void onCreate(SQLiteDatabase db) {
        String regraCriacaoTabela = "CREATE TABLE " + TABELA_USUARIO + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOME_USUARIO + " TEXT, " + IDADE_USUARIO + " INT, " + EHVIZINHO + " BOOL)";

        db.execSQL(regraCriacaoTabela);

    }

    // aqui é chamado quando tem um upgrade no DB para melhorar o upgrade de versionamento
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int versaoAntiga, int versaoNova) {

    }

    public boolean adicionaUm (PessoasModelo pessoasModelo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NOME_USUARIO, pessoasModelo.getNome());
        cv.put(IDADE_USUARIO, pessoasModelo.getIdade());
        cv.put(EHVIZINHO, pessoasModelo.isEhVizinho());

        long insert = db.insert(TABELA_USUARIO, null, cv);
            if (insert == -1){
                return false;
            }
            else {
                return true;
            }

    }

    public boolean deletaUm(PessoasModelo pessoasModelo){
      // encontra o pessoasModelo no banco de dados, se encontrado, deleta e retorna verdadeiro.
      // se não encontrado, retorna falso.

      SQLiteDatabase db = this.getWritableDatabase();
      String operacaoSQL = "DELETE FROM " + TABELA_USUARIO + " WHERE " + ID + " = " + pessoasModelo.getId();
        Cursor cursor = db.rawQuery(operacaoSQL, null);

        if(cursor.moveToFirst()){
            return true;
        }
        else {
            return  false;
        }
    }

    public List<PessoasModelo> getTodasPessoas() {
        List<PessoasModelo> returnList = new ArrayList<>();

        String comandoConsulta = "SELECT * FROM " + TABELA_USUARIO;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(comandoConsulta, null);

        if (cursor.moveToFirst()){
            // interage o cursor (resultado) e cria um novo objeto usuario. colocando ele na lista de retorno
            do {
                int usuarioID = cursor.getInt(0); // 0 é a coluna na tabela do banco de dados
                String usuarioNome = cursor.getString(1);
                int usuarioIdade = cursor.getInt(2);
                boolean ehVizinho = cursor.getInt(3) == 1 ? true: false;

                PessoasModelo novaPessoa = new PessoasModelo(usuarioID, usuarioNome, usuarioIdade, ehVizinho);
                returnList.add(novaPessoa);

            } while (cursor.moveToNext());
        }
        else {
            // se falhou, não faz nada.
        }

        // sempre fechar a conexão com o banco de dados após o uso
        cursor.close();
        db.close();


        return  returnList;
    }
}
