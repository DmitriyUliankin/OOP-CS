package Data.Repository;

import Data.Converters.IEntityConverter;
import Data.Entities.IEntity;
import Exceptions.Entities.EntityNotFoundException;
import lombok.NonNull;

import java.io.*;
import java.util.ArrayList;

public abstract class FileRepositoryBase<TKey, TEntity extends IEntity<TKey>>
    extends InMemoryRepositoryBase<TKey, TEntity>
{
    public FileRepositoryBase(@NonNull String filepath, @NonNull IEntityConverter<TEntity, String> converter)
    {
        super();
        _filepath = filepath;
        _converter = converter;

        if(_collection == null || _collection.isEmpty())
        {
            try {
                ReadFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected @NonNull String _filepath;
    protected @NonNull IEntityConverter<TEntity, String> _converter;

    @Override
    public void Update(TEntity entity) throws EntityNotFoundException {
        super.Update(entity);
        WriteFile();
    }

    @Override
    public void Create(TEntity entity)
    {
        //todo: super.Create should throw an exception if something went wrong so that we don't need to write the unchanged array to a file
        super.Create(entity);
        WriteFile();
    }

    protected void ReadFile() throws IOException {
        FileReader reader = new FileReader(_filepath);
        BufferedReader bufferedReader = new BufferedReader(reader);
        ArrayList<String> stringList = new ArrayList<>();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            stringList.add(line);
        }
        reader.close();

        _collection = new ArrayList<>();
        for (String s : stringList) {
            _collection.add(_converter.ConvertReverse(s));
        }
    }

    protected void WriteFile(){
        StringBuilder sb = new StringBuilder();

        for (TEntity entity: _collection) {
            sb.append(_converter.Convert(entity)).append(System.getProperty("line.separator"));
        }

        FileWriter writer;
        try {
            writer = new FileWriter(_filepath);
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
