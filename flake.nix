{
  inputs.nixpkgs.url = "github:NixOS/nixpkgs/nixos-26.05";

  outputs = { self, nixpkgs }:
    let
      system = "x86_64-linux";
      pkgs = nixpkgs.legacyPackages.${system};
    in {
      devShells.${system}.default = pkgs.mkShell {
        buildInputs = with pkgs; [ jdk21 maven gradle git gh ];
        
        shellHook = ''
          export JAVA_HOME="${pkgs.jdk21}/lib/openjdk"
          export JAVA_OPTS="-Xmx2G -Xms512M"
          export LD_LIBRARY_PATH="${pkgs.lib.makeLibraryPath [ pkgs.stdenv.cc.cc pkgs.zlib ]}"
          
          echo "🌱 Spring Boot Environment Activated (Java 21)"
          java -version
        '';
      };
    };
}
