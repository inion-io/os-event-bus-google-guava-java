package io.inion.os.messaging.eventbus.skills;

import io.inion.os.messaging.eventbus.google.EventBus;
import java.lang.reflect.Method;
import io.inion.os.common.SiCell;
import io.inion.os.common.annotation.CellType;
import io.inion.os.common.exception.CellRunException;
import io.inion.os.common.messaging.annotation.Event;

@CellType(
    objectClass = SkCheckAndRegister.SkCheckAndRegisterObject.class,
    type = SkCheckAndRegister.CELL_TYPE,
    uuid = SkCheckAndRegister.CELL_UUID
)
public interface SkCheckAndRegister extends SiCell<SkCheckAndRegister, Void> {

  String CELL_TYPE = "event-bus-check-and-register-skill";

  String CELL_UUID = "4FAE8365-9642-48FF-9BE1-8AE16CF5D773";

  SkCheckAndRegister setCell(SiCell<?, ?> cell);

  SkCheckAndRegister setEventBus(EventBus eventBus);

  class SkCheckAndRegisterObject extends SiCellObject<SkCheckAndRegister, Void> implements SkCheckAndRegister {

    private SiCell<?, ?> cell;
    private EventBus eventBus;

    @Override
    public SkCheckAndRegister run() throws CellRunException {
      checkRunValuesForNull(eventBus, cell);

      for (Method method : cell.getClass().getDeclaredMethods()) {
        if (method.isAnnotationPresent(Event.class)) {
          eventBus.register(cell);
          break;
        }
      }

      return getSelf();
    }

    @Override
    public SkCheckAndRegister setCell(SiCell<?, ?> cell) {
      this.cell = cell;
      return getSelf();
    }

    @Override
    public SkCheckAndRegister setEventBus(EventBus eventBus) {
      this.eventBus = eventBus;
      return getSelf();
    }
  }
}
