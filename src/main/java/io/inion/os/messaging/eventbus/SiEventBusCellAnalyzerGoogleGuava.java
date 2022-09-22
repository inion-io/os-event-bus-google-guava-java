package io.inion.os.messaging.eventbus;

import com.google.gson.JsonObject;
import io.inion.os.common.annotation.CellReference;
import io.inion.os.common.annotation.CellType;
import io.inion.os.common.discovery.analyzer.SiCellAnalyzer;

@CellType(
    objectClass = SiEventBusCellAnalyzerGoogleGuava.SiEventBusCellAnalyzerGoogleGuavaObject.class,
    type = SiEventBusCellAnalyzerGoogleGuava.CELL_TYPE,
    uuid = SiEventBusCellAnalyzerGoogleGuava.CELL_UUID
)
public interface SiEventBusCellAnalyzerGoogleGuava extends SiCellAnalyzer {

  String CELL_TYPE = "event-bus-google-guava-cell-analyzer";

  String CELL_UUID = "4FAE8365-9642-48FF-9BE1-8AE16CF5D773";

  class SiEventBusCellAnalyzerGoogleGuavaObject extends
      SiCellAnalyzer.SiCellAnalyzerObject implements SiEventBusCellAnalyzerGoogleGuava {

    @CellReference
    private SiEventBusGoogleGuava eventBus;

    @Override
    public void doRun() {
      eventBus.checkAndRegister(cell);
    }

    @Override
    public JsonObject toJsonObject() {
      // TODO: Hack für RemoteDiscovery
      return new JsonObject();
    }
  }
}
