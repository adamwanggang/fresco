/*
 * This file provided by Facebook is for non-commercial testing and evaluation
 * purposes only.  Facebook reserves all rights not expressly granted.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * FACEBOOK BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.facebook.samples.scrollperf.data;

/**
 * This is a simple version of an Adapter which just provides the number of element and
 * the element itself based on an index
 */
public interface SimpleAdapter<E> {

  int getSize();

  E get(int position);

  boolean isLazy();

  /**
   * Utility class for SimpleAdapter
   */
  class Util {

    /**
     * This creates an infinite version of the given SimpleAdapter setting if its lazy or not
     * @param srcAdapter The source SimpleAdapter
     * @param <E> The parameter type for this SimpleAdapter
     * @return The infinite version of this SimpleAdapter
     */
    public static <E> SimpleAdapter<E> makeItInfinite(final SimpleAdapter<E> srcAdapter) {
      if (srcAdapter.getSize() == Integer.MAX_VALUE) {
        return srcAdapter;
      }
      return new SimpleAdapter<E>() {
        @Override
        public int getSize() {
          return Integer.MAX_VALUE;
        }

        @Override
        public E get(int position) {
          return srcAdapter.get(position % srcAdapter.getSize());
        }

        @Override
        public boolean isLazy() {
          return srcAdapter.isLazy();
        }
      };
    }
  }
}
