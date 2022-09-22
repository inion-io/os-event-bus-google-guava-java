package io.inion.os.messaging.eventbus.skills;

import io.inion.os.messaging.eventbus.google.EventBus;
import io.inion.os.common.SiCell;
import io.inion.os.common.annotation.CellType;
import io.inion.os.common.exception.CellRunException;
import io.inion.os.messaging.eventbus.skills.SkUnregister.SkUnregisterObject;

@CellType(
    objectClass = SkUnregisterObject.class,
    type = SkUnregister.CELL_TYPE,
    uuid = SkUnregister.CELL_UUID
)
public interface SkUnregister extends SiCell<SkUnregister, Void> {

  String CELL_TYPE = "event-bus-unregister-skill";

  String CELL_UUID = "4FAE8365-9642-48FF-9BE1-8AE16CF5D773";

  SkUnregister setEventBus(EventBus eventBus);

  SkUnregister setListener(SiCell<?, ?> listener);

  class SkUnregisterObject extends SiCellObject<SkUnregister, Void> implements SkUnregister {

    private EventBus eventBus;
    private SiCell<?, ?> listener;

    @Override
    public SkUnregister run() throws CellRunException {
      checkRunValuesForNull(eventBus, listener);

      eventBus.unregister(listener);

      return getSelf();
    }

    @Override
    public SkUnregister setEventBus(EventBus eventBus) {
      this.eventBus = eventBus;
      return getSelf();
    }

    @Override
    public SkUnregister setListener(SiCell<?, ?> listener) {
      this.listener = listener;
      return getSelf();
    }
  }
}
