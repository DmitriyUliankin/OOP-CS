package Data.Repository;

import Data.Converters.IEntityConverter;
import Data.Entities.IEntity;
import Exceptions.Entities.EntityAlreadyExistException;
import Exceptions.Entities.EntityNotFoundException;
import lombok.NonNull;

import java.io.*;
import java.util.ArrayList;

public abstract class FileRepositoryBase<TKey, TEntity extends IEntity<TKey>>
    extends InMemoryRepositoryBase<TKey, TEntity>
{
    public FileRepositoryBase(@NonNull String filepath)
    {
        super();
        _filepath = filepath;

        if(_collection == null || _collection.isEmpty())
        {
            ReadFile();
        }
    }

    protected @NonNull String _filepath;
    protected abstract IEntityConverter<TEntity, String> get_converter();

    @Override
    public void Update(TEntity entity) throws EntityNotFoundException {
        super.Update(entity);
        WriteFile();
    }

    @Override
    public void Create(TEntity entity) throws EntityAlreadyExistException {
        super.Create(entity);
        WriteFile();
    }

    protected void ReadFile()
    {
        _collection = new ArrayList<>();

        try
        {
            FileReader reader = new FileReader(_filepath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            ArrayList<String> stringList = new ArrayList<>();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringList.add(line);
            }

            reader.close();

            if(stringList.isEmpty())
                return;

            for (String s : stringList) {
                if(s != null || !s.isEmpty())
                {
                    TEntity entity = get_converter().ConvertReverse(s);
                    _collection.add(entity);
                }
            }
        }
        catch (IOException e) {}
    }

    protected void WriteFile(){
        StringBuilder sb = new StringBuilder();

        for (TEntity entity: _collection) {
            sb.append(get_converter().Convert(entity)).append(System.getProperty("line.separator"));
        }

        FileWriter writer;
        try {
            writer = new FileWriter(_filepath,false);
            writer.write(sb.toString());
            writer.flush();
        } catch (IOException e) { }
    }
}
