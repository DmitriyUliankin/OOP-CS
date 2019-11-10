package Data.Repository.Csv;

import Data.Converters.IEntityConverter;
import Data.Entities.IEntity;
import Data.Repository.FileRepositoryBase;
import lombok.NonNull;

public class CsvRepository<TKey, TEntity extends IEntity<TKey>>
    extends FileRepositoryBase<TKey, TEntity>
{
    //todo: use csvConverter
    public CsvRepository(@NonNull String filepath, IEntityConverter<TEntity, String> _converter) {
        super(filepath, _converter);
    }
}
