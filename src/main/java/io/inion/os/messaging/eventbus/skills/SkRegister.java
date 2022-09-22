package io.inion.os.messaging.eventbus.skills;

import io.inion.os.messaging.eventbus.google.EventBus;
import io.inion.os.common.SiCell;
import io.inion.os.common.annotation.CellType;
import io.inion.os.common.exception.CellRunException;
import io.inion.os.messaging.eventbus.skills.SkRegister.SkRegisterObject;

@CellType(
    objectClass = SkRegisterObject.class,
    type = SkRegister.CELL_TYPE,
    uuid = SkRegister.CELL_UUID
)
public interface SkRegister extends SiCell<SkRegister, Void> {

  String CELL_TYPE = "event-bus-register-skill";

  String CELL_UUID = "4FAE8365-9642-48FF-9BE1-8AE16CF5D773";

  SkRegister setEventBus(EventBus eventBus);

  SkRegister setListener(SiCell<?, ?> listener);

  class SkRegisterObject extends SiCellObject<SkRegister, Void> implements SkRegister {

    private EventBus eventBus;
    private SiCell<?, ?> listener;

    @Override
    public SkRegister run() throws CellRunException {
      checkRunValuesForNull(eventBus, listener);

      eventBus.register(listener);

      return getSelf();
    }

    @Override
    public SkRegister setEventBus(EventBus eventBus) {
      this.eventBus = eventBus;
      return getSelf();
    }

    @Override
    public SkRegister setListener(SiCell<?, ?> listener) {
      this.listener = listener;
      return getSelf();
    }
  }
}
