package ProcessPackage;

public enum Functions {
    Seed_Layer(0), Buffer(1), N_Buffer(2), QW(3), Barrier(4), P_Layer(5), EBLayer(6), SuperLattice(7), InterLayer(8);
      private int index;
      Functions(int i) {index=i;}
      public int getIndex() {return index;}
}
