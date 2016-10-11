package collision_maps;

/**
 * Created by jens on 2/16/16.
 */
public interface Body extends Intersect {
	boolean collide(Body other);
}
