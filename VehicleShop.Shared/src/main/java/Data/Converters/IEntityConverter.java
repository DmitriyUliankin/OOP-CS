package Data.Converters;

public interface IEntityConverter<TEntity, TModel> {
    TEntity ConvertReverse(TModel model);
    TModel Convert(TEntity entity);
}
