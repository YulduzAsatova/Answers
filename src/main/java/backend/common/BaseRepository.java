package backend.common;
import java.io.*;
import java.util.List;

public abstract class BaseRepository <ENTITY extends BaseEntity<ID>,ID> implements Repository<ENTITY,ID>{


    protected abstract String getFilePath();

    @Override
    public ENTITY save(ENTITY entity) {
        List<ENTITY> entities = readFromFile();
        entities.add(entity);
        writeToFile(entities);

        return entity;
    }

    @Override
    public ENTITY findById(ID id) {
        for(ENTITY entity:readFromFile()){
            if(entity.getId().equals(id)){
                return entity;
            }
        }
        return null;
    }

    @Override
    public List<ENTITY> getAll() {
        return readFromFile();
    }

    @Override
    public void deleteById(ID id) {
        List<ENTITY> entities = readFromFile();
        for(ENTITY entity:entities){
            if(entity.getId().equals(id)){
                entities.remove(entity);
                writeToFile(entities);
            }
        }
    }

    protected List<ENTITY> readFromFile()
    {
        try( ObjectInputStream stream = new ObjectInputStream( new FileInputStream( getFilePath() ) ) )
        {
            return (List<ENTITY>) stream.readObject();
        }
        catch( IOException | ClassNotFoundException e)
        {
            throw new RuntimeException( e );
        }

    }
    protected void writeToFile( List<ENTITY> entities )
    {
        try( ObjectOutputStream stream = new ObjectOutputStream( new FileOutputStream( getFilePath() ) ) )
        {
            stream.writeObject( entities );
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
    }
}
