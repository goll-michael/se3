package muster5_bsp_neu;

import java.util.Date;

public class RealesDatum implements AbstraktesDatum {
	@Override
	public void ausgeben() {
		System.out.println(new Date());
	}
}
