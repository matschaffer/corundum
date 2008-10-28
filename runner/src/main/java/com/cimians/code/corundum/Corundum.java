package com.cimians.code.corundum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.jruby.Ruby;
import org.jruby.RubyRuntimeAdapter;
import org.jruby.javasupport.JavaEmbedUtils;

public class Corundum {
  /*
    # Loader example
    class ResourceLoader

      def read(path)
        path = "#{BASE_RESOURCE_PATH}/#{path}"
        is = resource.get_resource_as_stream path
        bis = java.io.BufferedInputStream.new(is)
        bytes = ''
        while (byte = bis.read) != -1
          bytes << byte.chr
        end
        bis.close
        bytes
      end

      private
      def resource
        @resource ||= java.lang.Class.for_name('java.lang.String')
        @resource
      end
    end
   */
   
	public static void main(String[] args) throws NullPointerException,
			IOException {
		Ruby runtime = JavaEmbedUtils.initialize(new ArrayList());
		RubyRuntimeAdapter evaler = JavaEmbedUtils.newRuntimeAdapter();
		
		InputStream main = Corundum.class.getClassLoader().getResourceAsStream("main.rb");
		BufferedReader reader = new BufferedReader(new InputStreamReader(main));
		String mainScript = "";
		String line;
		while ((line = reader.readLine()) != null) {
			mainScript += line + "\n";
		}
		
		evaler.eval(runtime, mainScript);
		
		JavaEmbedUtils.terminate(runtime);
	}
}
